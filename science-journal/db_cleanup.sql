delete FROM ACT_GE_BYTEARRAY where NAME_='Registration_diagram.bpmn';
delete FROM ACT_GE_BYTEARRAY where NAME_='Process_diagram.bpmn';
delete FROM ACT_GE_BYTEARRAY where NAME_='Subscription_diagram.bpmn';

delete FROM ACT_HI_ACTINST where PROC_DEF_KEY_='Registration';
delete FROM ACT_HI_ACTINST where PROC_DEF_KEY_='Paper_apply_process';
delete FROM ACT_HI_ACTINST where PROC_DEF_KEY_='Subscription';

delete FROM ACT_HI_DETAIL where NAME_='Registration';
delete FROM ACT_HI_DETAIL where NAME_='Paper_apply_process';
delete FROM ACT_HI_DETAIL where NAME_='Subscription';

delete FROM ACT_HI_IDENTITYLINK where PROC_DEF_KEY_='Registration';
delete FROM ACT_HI_IDENTITYLINK where PROC_DEF_KEY_='Paper_apply_process';
delete FROM ACT_HI_IDENTITYLINK where PROC_DEF_KEY_='Subscription';

delete FROM ACT_HI_PROCINST where PROC_DEF_KEY_='Registration';
delete FROM ACT_HI_PROCINST where PROC_DEF_KEY_='Paper_apply_process';
delete FROM ACT_HI_PROCINST where PROC_DEF_KEY_='Subscription';

delete FROM ACT_HI_TASKINST where PROC_DEF_KEY_='Registration';
delete FROM ACT_HI_TASKINST where PROC_DEF_KEY_='Paper_apply_process';
delete FROM ACT_HI_TASKINST where PROC_DEF_KEY_='Subscription';

delete FROM ACT_HI_VARINST where PROC_DEF_KEY_='Registration';
delete FROM ACT_HI_VARINST where PROC_DEF_KEY_='Paper_apply_process';
delete FROM ACT_HI_VARINST where PROC_DEF_KEY_='Subscription';

delete FROM ACT_RE_DEPLOYMENT where SOURCE_ not like '%process application%';

--delete from ACT_RU_VARIABLE where referential integrity error

delete FROM ACT_RU_EXECUTION where PROC_DEF_ID_ like 'Registration%';
delete FROM ACT_RU_EXECUTION where PROC_DEF_ID_ like 'Paper_apply_process%';
delete FROM ACT_RU_EXECUTION where PROC_DEF_ID_ like 'Subscription%';

delete FROM ACT_RE_PROCDEF where RESOURCE_NAME_='Registration_diagram.bpmn';
delete FROM ACT_RE_PROCDEF where RESOURCE_NAME_='Process_diagram.bpmn';
delete FROM ACT_RE_PROCDEF where RESOURCE_NAME_='Subscription_diagram.bpmn';

delete from ACT_RU_AUTHORIZATION where ID_ is not null;
delete from ACT_RU_METER_LOG where ID_ is not null;




