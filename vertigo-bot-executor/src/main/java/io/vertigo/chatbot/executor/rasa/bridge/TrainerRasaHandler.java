package io.vertigo.chatbot.executor.rasa.bridge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import io.vertigo.chatbot.commons.domain.TrainerInfo;
import io.vertigo.chatbot.executor.domain.RasaConfig;
import io.vertigo.core.component.Activeable;
import io.vertigo.core.component.Component;
import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.dynamo.impl.file.model.FSFile;
import io.vertigo.lang.VSystemException;
import io.vertigo.lang.VUserException;

public class TrainerRasaHandler extends AbstractRasaHandler implements Component, Activeable {

	private static final String TRAINING_MODEL_DIR = "trainingModel";

	private Process rasaTrainingProcess;
	private StringBuilder trainingLog;
	private Instant startTime;
	private Instant endTime;

	@Override
	public void start() {
		// nothing
	}

	@Override
	public void stop() {
		// stop child process
		if (rasaTrainingProcess.isAlive()) {
			rasaTrainingProcess.destroyForcibly();
		}
	}


	public void trainModel(final RasaConfig config, final Long id) {
		if (isTraining()) {
			throw new VUserException("Training already in progress");
		}
		startTime = Instant.now();
		endTime = null;

		writeToRasaFile(config.getDomain(), "domain.yml");
		writeToRasaFile(config.getStories(), "data/stories.md");
		writeToRasaFile(config.getNlu(), "data/nlu.md");

		trainingLog = new StringBuilder();
		rasaTrainingProcess = execRasa("train", trainingLog::append, () -> postTrainModel(id),
				"--out", TRAINING_MODEL_DIR,
				"--fixed-model-name", id.toString()
				);
	}

	private void postTrainModel(final Long id) {
		LOGGER.info("Training complete !");

		endTime = Instant.now();

		// TODO callback to designer
		getModelPath(id).toFile().exists();
	}

	public TrainerInfo getState() {
		final TrainerInfo retour = new TrainerInfo();

		retour.setName("Rasa node");
		retour.setTrainingInProgress(isTraining());
		retour.setLatestTrainingLog(getTrainingLog());
		retour.setStartTime(startTime);
		retour.setEndTime(endTime);

		return retour;
	}

	private boolean isTraining() {
		return rasaTrainingProcess != null && rasaTrainingProcess.isAlive();
	}

	private String getTrainingLog() {
		if (trainingLog == null) {
			return "";
		}
		return trainingLog.toString();
	}


	private void writeToRasaFile(final String content, final String relativePath) {
		try (FileOutputStream outputStream = new FileOutputStream(BOT_PATH + "/" + relativePath)) {
			outputStream.write(content.getBytes(StandardCharsets.UTF_8));
		} catch (final IOException e) {
			throw new VSystemException(e, "Impossible d'écrire le fichier de configuration de Rasa");
		}
	}

	public VFile getModel(final Long id) {
		try {
			return new FSFile(id + ".zip", "application/zip", getModelPath(id));
		} catch (final IOException e) {
			throw new VSystemException(e, "Impossible de lire le fichier du modèle");
		}
	}

	public boolean delModel(final Long id) {
		return getModelPath(id).toFile().delete();
	}

	private Path getModelPath(final Long id) {
		return Paths.get(BOT_PATH, TRAINING_MODEL_DIR, + id + ".tar.gz");
	}

	public void stopTrain() {
		LOGGER.info("Training aborted !");
		stop();
	}
}
