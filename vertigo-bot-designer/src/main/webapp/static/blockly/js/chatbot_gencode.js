function isComposite(block) {
	return block.statementInputCount > 0;
}

function formatVariable(type, nomVar){
	if(type=="global/context/url"){
		// this.getParameter("condition-variable").setText("E");
		return ' /user/'+ type + ' ';
	}
	else{
		return ' /user/'+ type + '/' + nomVar.toLowerCase() + ' ';
	}
}

function formatString(nomVar){
	return ' "' + nomVar+ '" ';
}

function formatString(nomVar, state){
	state = isNaN(nomVar)
	if(state===true) return ' "' + nomVar+ '" ';
	else return ' '+nomVar+' ';
}

function formatStringOrNumber(nomVar, state){
	if(state===true) return ' "' + nomVar+ '" ';
	else return ' '+nomVar+' ';
}

function extractParams(block) {
	let params = "";
	block.inputList.forEach(input => {
		input.fieldRow.forEach(param => {
			if (param.EDITABLE && !param.name.startsWith(':')) {
				let value = param.getValue().replace(/\r?\n/g, "<br />").replace('"','\\"');
				if (value.includes(' ') || value.includes('"')) {
					params += ' "' + value + '"';
				} else {
					params += ' ' + value;
				}
			}
		});
		if (input.type !== Blockly.inputTypes.STATEMENT && input.connection && input.connection.targetBlock()) {
			params += extractParams(input.connection.targetBlock());
		}
	});
	return params;
}

function getBlockName(block) {
	let nameParam = block.getFieldValue(':NAME');
	let name = nameParam ? nameParam : block.type.replace(/^cb_/,'');

	let optValue = block.getFieldValue(':QUALIFY');
	if (optValue) {
		name += optValue
	}
	return name;
}

function buildBlock(block) {
	let name = getBlockName(block);
	let params = extractParams(block);


	if (isComposite(block)) {
		let subBlocks = Blockly.BotScript.statementToCode(block, 'SUB_BLOCKS')
		return 'begin ' + name + params + '\n' + subBlocks + 'end ' + name + '\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
	}

	return name + params + '\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}

function toCode(event) {
	let topBlocks = Blockly.mainWorkspace.getTopBlocks().filter(b => b.rendered === true && b.disabled === false);
	let topBlocksCount = topBlocks.length;
	let startCount = topBlocks.filter(b => b.type === "cb_start").length;
	let isOnlySequence = topBlocksCount === 1 && topBlocks[0].type === "cb_sequence";

	// document.getElementById('error_blockly').innerHTML = '';
	var code = Blockly.BotScript.workspaceToCode(Blockly.mainWorkspace );

	if (startCount === 0 && !isOnlySequence) {
		if (code) {
			code = Blockly.BotScript.prefixLines(code, Blockly.BotScript.INDENT);
		}
		code = 'begin sequence\n' + code + 'end sequence';
	}
	VertigoUi.vueData.scriptIntention.script = code;
}

function getCodeDiagram(event) {
	let topBlocks = Blockly.mainWorkspace.getTopBlocks().filter(b => b.rendered === true && b.disabled === false);
	let topBlocksCount = topBlocks.length;
	let startCount = topBlocks.filter(b => b.type === "cb_start").length;
	let isOnlySequence = topBlocksCount === 1 && topBlocks[0].type === "cb_sequence";

	// document.getElementById('error_blockly').innerHTML = '';
	var code = Blockly.BotScript.workspaceToCode(Blockly.mainWorkspace );

	if (startCount === 0 && !isOnlySequence) {
		if (code) {
			code = Blockly.BotScript.prefixLines(code, Blockly.BotScript.INDENT);
		}
		code = 'begin sequence\n' + code + 'end sequence';
	}
	return code
}


Blockly.BotScript = new Blockly.Generator('BotScript');

for (name in Blockly.Blocks) { // same rendering function for all CB blocks
	if (name.startsWith('cb_')) Blockly.BotScript[name] = buildBlock;
}

// exceptions
// - start block
// Blockly.BotScript['cb_start'] = function(block) {
// 	return 'begin sequence\n' + Blockly.BotScript.prefixLines(Blockly.BotScript.blockToCode(block.getNextBlock()), Blockly.BotScript.INDENT) + 'end sequence\n';
// }

// - generic arguments
Blockly.BotScript['cb_:generic_arg'] = function(block) {
	return null;
}

// *****************************
// *****CATEGORIE SEQUENCE******
// *****************************

// *****************************
// *****CATEGORIE SELECTOR******
// *****************************
// cb_condition
// <typeCondition> <key> <value> // si n'est pas condition fulfilled
// fulfilled <key> // si est condition fulfilled
Blockly.BotScript['cb_condition'] = function(block) {
	let params = getBlockParams(block);
	let condition = params[0].getValue();
	let name = getBlockName(block);
	let isAFulfilledCondition;
	if(condition=="fulfilled")isAFulfilledCondition=true;
	else isAFulfilledCondition =false;
	let isAStringCondition;
	if(condition=="eq"||condition=="contains")isAStringCondition=true;
	else isAStringCondition =false;
	if(isAFulfilledCondition){
		return condition + formatVariable(params[1].getValue(),params[2].getValue())+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
	}
	else if(isAStringCondition){
		return condition + formatVariable(params[1].getValue(),params[2].getValue()) + ' "' + params[3].getValue()+'"'+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
	}
	else{
		return condition + formatVariable(params[1].getValue(),params[2].getValue()) + ' ' + params[3].getValue()+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
	}
}



// *****************************
// ******CATEGORIE BUTTON*******
// *****************************
// begin choose:button<:nlu|| > <key> <"question"> ___ end choose:button<:nlu|| >
// variable en miniscule
Blockly.BotScript['cb_buttons'] = function(block) {
	let params = getBlockParams(block);
	let subBlocks = Blockly.BotScript.statementToCode(block, 'SUB_BLOCKS')
	let isOption = false;
	if(params[3].getValue()==='nlu') isOption = true;
	return "begin choose:button" +  (isOption ? ':' +params[3].getValue()  : '') + formatVariable(params[1].getValue(),params[2].getValue()) + ' "'+ params[0].getValue()+'"' +"\n" + subBlocks + 'end ' + "choose:button"+(isOption ? ':' +params[3].getValue()  : '')  +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_button'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	return name +' "'+ params[0].getValue() +'" ' + params[1].getValue() +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}



// *****************************
// ******CATEGORIE TOPIC********
// *****************************
// cb_topic_start
// topic:start
Blockly.BotScript['cb_topicstart'] = function(block) {
	return "topic:start\n"+ Blockly.BotScript.blockToCode(block.getNextBlock());
}
// cb_topic_start
// topic:fallback
Blockly.BotScript['cb_topicfallback'] = function(block) {
	return "topic:fallback\n"+ Blockly.BotScript.blockToCode(block.getNextBlock());
}
// cb_topic_idle
// topic:idle
Blockly.BotScript['cb_topicidle'] = function(block) {
	return "topic:idle\n"+ Blockly.BotScript.blockToCode(block.getNextBlock());
}

// *****************************
// *****CATEGORIE SWITCHCASE****
// *****************************
// cb_switch
// begin switch /user/<'local'||'global'>/<nomVariable> ___ end switch
// variable en minuscule
Blockly.BotScript['cb_switch'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	let subBlocks = Blockly.BotScript.statementToCode(block, 'SUB_BLOCKS')
	return 'begin ' + name + formatVariable(params[0].getValue(), params[1].getValue()) + '\n' + subBlocks + 'end ' + name + '\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}

// cb_case
// begin case <"valeur"> ___ case switch
Blockly.BotScript['cb_case'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	let subBlocks = Blockly.BotScript.statementToCode(block, 'SUB_BLOCKS')
	return 'begin ' + name + formatString(params[0].getValue(), true) +'\n' + subBlocks + 'end ' + name + '\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}

// *****************************
// ******CATEGORIE MESSAGE******
// *****************************
Blockly.BotScript['cb_say'] = function(block) {
	let params = getBlockParams(block);
	let isOption = true;
	if(params[1].getValue()=="") isOption = false;

	return "say" + (isOption ? ':' +params[1].getValue()  : '')+' "' +params[0].getValue() + '"' +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_link'] = function(block) {
	let params = getBlockParams(block);
	return "link" + ' "' +params[0].getValue() + '"' +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_mail'] = function(block) {
	let params = getBlockParams(block);
	// console.log(params) //todo correction des mails multi destinataires
	let isPJMail = params[4].getValue()==='yespj'
	return "mail" +  (!isPJMail ? "" : ":attachment") +
		formatVariable(params[0].getValue(), params[1].getValue()) +
		formatVariable(params[2].getValue(), params[3].getValue())+
		(!isPJMail ? "" : formatVariable(params[5].getValue(), params[6].getValue()))+
		formatVariable(params[7].getValue(), params[8].getValue())+ '\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}


// *****************************
// ****CATEGORIE ACTION-KEY*****
// *****************************
Blockly.BotScript['cb_set'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	let isNumber = params[2].getValue()==='number'
	return name+ (!isNumber ? "" : "Int") + formatVariable(params[0].getValue(), params[1].getValue()) + formatStringOrNumber(params[3].getValue(), !isNumber)+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_copy'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	return name +formatVariable(params[0].getValue(), params[1].getValue()) + formatVariable(params[2].getValue(), params[3].getValue()) +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_incrBy'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	let isIncr = params[0].getValue()==='incrBy'
	return (isIncr ? 'incrBy' : 'decr') +formatVariable(params[1].getValue(), params[2].getValue()) + params[3].getValue() +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_remove'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	return name +formatVariable(params[0].getValue(), params[1].getValue()) +'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}

// *****************************
// ******CATEGORIE OTHER*******
// *****************************
// Blockly.BotScript['cb_random'] = function(block) {
// 	let subBlocks = Blockly.BotScript.statementToCode(block, 'SUB_BLOCKS')
// 	return 'begin random\n' + subBlocks + 'end random\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
// }
Blockly.BotScript['cb_append'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	return name +formatVariable(params[0].getValue(), params[1].getValue()) +  formatString(params[2].getValue())+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
Blockly.BotScript['cb_inputString'] = function(block) {
	let params = getBlockParams(block);
	let name = getBlockName(block);
	return name +formatVariable(params[1].getValue(), params[2].getValue()) +  formatString(params[0].getValue())+'\n' + Blockly.BotScript.blockToCode(block.getNextBlock());
}
