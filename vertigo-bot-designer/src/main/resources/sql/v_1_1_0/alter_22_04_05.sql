ALTER TABLE TYPE_EXPORT_ANALYTICS ADD COLUMN BOT_RELATED bool not null default false;

comment on column TYPE_EXPORT_ANALYTICS.BOT_RELATED is
'Bot related';

UPDATE TYPE_EXPORT_ANALYTICS SET BOT_RELATED = true WHERE TEA_CD = 'UNKNOWN_MESSAGES';
UPDATE TYPE_EXPORT_ANALYTICS SET BOT_RELATED = false WHERE TEA_CD = 'SESSIONS';