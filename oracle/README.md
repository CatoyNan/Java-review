sysDbA 数据库处理密码过期问题
ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;

alter user dlty identified by 123456 account unlock;



查看用户是否被锁
select username,account_status from dba_users where username='IFBM_ZZ'; 
alter user IFBM_ZZ account unlock; 

-----依次执行以下步骤
update TA_ROLE_FUNCTION_MAP set role_id =REPLACE(role_id ,substr(role_id ,0,2) ,'DL') where role_id is not null ;
远程连接88那台电脑，登录密码123456，su -l oracle 切换数据库用户
--1.删除已有的用户
 drop user hssc cascade;
drop user ifbm cascade;
--2.创建空间
 create tablespace hssc DATAFILE '/home/oracle/hssc.DBF' size 1024M autoextend on next 1024M maxsize unlimited;
create tablespace IFBM_BOSH DATAFILE '/home/oracle/IFBM_BOSH.DBF' size 1024M autoextend on next 1024M maxsize unlimited;
、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
create tablespace OPICS_DATA DATAFILE 'D:\workspace\OPICS_DATA.DBF' size 1024M autoextend on next 1024M maxsize unlimited;
 drop user DL_OPICS cascade;
 create user DL_OPICS identified by 123456 DEFAULT TABLESPACE OPICS_DATA;
--4.授权
grant connect, resource to DL_OPICS;
grant unlimited tablespace to DL_OPICS;
GRANT DEBUG ANY PROCEDURE ,DEBUG CONNECT SESSION TO DL_OPICS;
grant create view,create synonym,create sequence to DL_OPICS;
GRANT dba TO DL_OPICS;
、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
--3.创建用户密码
 create user IFBM_DL identified by 123456 DEFAULT TABLESPACE IFBM_DATA;
create user IFBM_SH identified by abcd1234 DEFAULT TABLESPACE IFBM_DATA;
--4.授权
grant connect, resource to IFBM_DL;
grant unlimited tablespace to IFBM_DL;
GRANT DEBUG ANY PROCEDURE ,DEBUG CONNECT SESSION TO IFBM_DL;
grant create view,create synonym,create sequence to IFBM_DL;
GRANT dba TO utest;--dba为最高级权限，可以创建数据库，表等。

--5.切换hssc用户登录执行2-ibbm脚本
=========================================================
使用方式：先解压，然后用notepad++打开、找到TABLESPACE "IFBMSIT"字样，替换成你目前数据库创建的表空间名字；假设叫“USERS”；
那么需要使用 替换功能，将 TABLESPACE "IFBMSIT" 替换为 TABLESPACE "USERS"；然后保存，再进行导入
------------------------------数据还原-------------------
imp hsopics/123456@128.8.100.101/OPICS full=y file=/home/oracle/dmp/opics_20180104.dmp ignore=y 
imp fms/single_qlb@128.8.100.101/OPICS full=y file=/home/oracle/dmp/fms_20160830.dmp ignore=y 
imp quote_qd/123456@128.8.100.101/OPICS full=y file=/home/oracle/dmp/position20170831.dmp ignore=y 

imp IFBM_SH/abcd1234@128.8.100.101/IFBM full=y file=/home/oracle/dmp/20180901ifbm.dmp ignore=y 
imp HF_GJS/123456@128.8.100.101/IFBM full=y file=/home/oracle/dmp/PMMS20171213.DMP ignore=y 
imp HF_GJS/123456@128.8.100.101/IFBM full=y file=/home/oracle/dmp/PMMS20171213.DMP ignore=y 
grant dba to IFBM_DL;

------------------------------数据库备份----------------------------
exp sl_trd_sh/123456@128.8.100.101/IFBM file=/home/oracle/databak/ifbmbak20180830.dmp full=y  上海银行数据库
exp tygl/123456@128.8.100.101/IFBM file=/home/oracle/databak/jsbank20180730.dmp full=y 
exp ifbm_dl/123456@128.8.100.101/IFBM file=/home/oracle/databak/jsbank20180911.dmp full=y
exp ifbm_dl/123456@128.8.100.101/IFBM file=/home/oracle/databak/dlbank20180911.dmp full=y
exp ifbm/abcd1234@128.8.100.101/IFBM file=/home/oracle/databak/ifbmbak20180827.dmp full=y
$exp tygl/123456@128.8.100.101/IFBM file=/home/oracle/databak/jsbank20180730.dmp owner=tygl
$exp ifbm_hf/123456@localhost/orcl file=D:\javasoft\dmp\ifbm_hf.dmp owner=ifbm_hf

system/123456 as sysdba
$exp ifbm_kl/123456@localhost/orcl file=D:\javasoft\dmp\ifbm_kl.dmp owner=ifbm_kl
$exp ifbm_dlty/123456@localhost/orcl file=D:\javasoft\dmp\ifbm_dlty.dmp owner=ifbm_dlty
------------------------------外网可访问资源---------------------------------------
http://183.129.146.22:7080/qlbfms/   sys1/abc123 
http://183.129.146.22:7080/CZBankQuoteServer/  A0474/888888  已删掉,与青岛项目冲突，同一tomcat只能启动一个
http://183.129.146.22:7080/BQD_QuoteServer/  admin/123456
http://183.129.146.22:7080/capitalweb/standard/  admin/123456(devp@1234)
----------------------------启动tomcat服务--------------------

1.打开SSH
2.输入128.8.100.101（root/SINGLEE@8848）
3.项目路径/home/apache-tomcat-6.0.45/webapps
4.替换对应的war包
5.重启服务
	killall -9 java 停止
	ps -ef |grep java 查看
	nohup ./startup.sh & 启动项目


方式一：直接启动 ./startup.sh
方式二：作为服务启动 nohup ./startup.sh &
方式三：控制台动态输出方式启动 ./catalina.sh run 动态地显示tomcat后台的控制台输出信息,Ctrl+C后退出并关闭服务
通过方式一、方式三启动的tomcat有个弊端，当客户端连接断开的时候，tomcat服务也会立即停止，通过方式二可以作为linux服务一直运行
通过方式一、方式二方式启动的tomcat，其日志会写到相应的日志文件中，而不能动态地查看tomcat控制台的输出信息与错误情况，
通过方式三可以以控制台模式启动tomcat服务，直接看到程序运行时后台的控制台输出信息，不必每次都要很麻烦的打开catalina.out日志文件进行查看，
这样便于跟踪查阅后台输出信息。tomcat控制台信息包括log4j和System.out.println()等输出的信息。
2，关闭tomcat服务 ./shutdown.sh
杀掉java相关程序进程：killall -9 java
查看java进程： ps -ef |grep java
---------------------------分割线以下不用关注--------------------------------------
SQL> conn /as sysdba 
SQL> shutdown immediate; 
SQL> startup mount 
SQL> ALTER SYSTEM ENABLE RESTRICTED SESSION; 
SQL> ALTER SYSTEM SET JOB_QUEUE_PROCESSES=0; 
SQL> ALTER SYSTEM SET AQ_TM_PROCESSES=0; 
SQL> alter database open; 
SQL> ALTER DATABASE CHARACTER SET ZHS16GBK; 
ORA-12712: new character set must be a superset of old character set 
提示我们的字符集：新字符集必须为旧字符集的超集，这时我们可以跳过超集的检查做更改： 
SQL> ALTER DATABASE character set INTERNAL_USE ZHS16GBK; 
--我们看到这个过程和之前ALTER DATABASE CHARACTER SET操作的内部过程是完全相同的，也就是说INTERNAL_USE提供的帮助
就是使Oracle数据库绕过了子集与超集的校验. 
SQL> shutdown immediate; 
SQL> startup

--ORACLE查询字符集
SQL> select userenv('language') from dual;  Oracle查看字符集
--sqlserver 查询字符集
select SERVERPROPERTY(N'edition') as Edition     --数据版本，如企业版、开发版等
	,SERVERPROPERTY(N'collation') as Collation   --数据库字符集
	,SERVERPROPERTY(N'servername') as ServerName --服务名
	,@@VERSION as Version   --数据库版本号
	,@@LANGUAGE AS Language  --数据库使用的语言

--删除表空间
SQL>alter database datafile '/home/oracle/sjyz.DBF' offline drop;
SQL> alter database open;
SQL> drop tablespace sjyz including contents;
SQL> startup



https://www.oschina.net/p/cron-generator

-------------------------------------------------
<build>
		<plugins>
			<plugin>
				<groupId>com.github.wvengen</groupId>
				<artifactId>proguard-maven-plugin</artifactId>
				<version>2.0.7</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>proguard</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<attach>true</attach>
					<attachArtifactClassifier>pg</attachArtifactClassifier>
					<!-- attach 的作用是在 install 与 deploy 时将生成的 pg 文件也安装与部署 -->
					<options> <!-- 详细配置方式参考 ProGuard 官方文档 -->
						<!--<option>-dontobfuscate</option> -->
						<option>-ignorewarnings</option> <!--忽略所有告警 -->
						<option>-dontshrink</option>   <!--不做 shrink -->
						<option>-dontoptimize</option> <!--不做 optimize -->
						<option>-dontskipnonpubliclibraryclasses</option>
						<option>-dontskipnonpubliclibraryclassmembers</option>

						<option>-repackageclasses com.singlee</option>
						<!--平行包结构（重构包层次），所有混淆的类放在 pg 包下 -->
	
						<!-- 以下为 Keep，哪些内容保持不变，因为有一些内容混淆后（a,b,c）导致反射或按类名字符串相关的操作失效 -->
	
						<option>-keep class **.package-info</option>
						<!--保持包注解类 -->
	
						<option>-keepattributes Signature</option>
						<!--JAXB NEED，具体原因不明，不加会导致 JAXB 出异常，如果不使用 JAXB 根据需要修改 -->
						<!-- Jaxb requires generics to be available to perform xml parsing 
							and without this option ProGuard was not retaining that information after 
							obfuscation. That was causing the exception above. -->
	
						<option>-keepattributes SourceFile,LineNumberTable,*Annotation*</option>
						<!--保持源码名与行号（异常时有明确的栈信息），注解（默认会过滤掉所有注解，会影响框架的注解） -->
	
						<option>-keepclassmembers enum com.singlee.** { *;}</option>
						<!--保持枚举中的名子，确保枚举 valueOf 可以使用 -->
	
						<option>-keep class com.singlee.accounting.** {*;}</option>
						<!--保持 Bean 类，（由于很多框架会对 Bean 中的内容做反射处理，请根据自己的业务调整） -->


					</options>
					<outjar>${project.build.finalName}-pg</outjar>
					<libs>
						<lib>${java.home}/lib/rt.jar</lib>
					</libs>
				</configuration>
			</plugin>
	
		</plugins>
	</build>



导出所有表数据
SELECT t1.Table_Name || chr(13) || t3.comments       AS "表名称及说明",
       --t3.comments                                 AS "表说明",
       t1.Column_Name                                AS "字段名称",
       t1.DATA_TYPE || '(' || t1.DATA_LENGTH || ')'  AS "数据类型",
       t1.NullAble                                   AS "是否为空",
       t2.Comments                                   AS "字段说明",
       t1.Data_Default "默认值"
       --t4.created                                  AS "建表时间"
  FROM cols t1
  LEFT JOIN user_col_comments t2 
         ON t1.Table_name = t2.Table_name
        AND t1.Column_Name = t2.Column_Name
  LEFT JOIN user_tab_comments t3 
         ON t1.Table_name = t3.Table_name
  LEFT JOIN user_objects t4 
         ON t1.table_name = t4.OBJECT_NAME
  WHERE NOT EXISTS (SELECT t4.Object_Name
          FROM User_objects t4
         WHERE t4.Object_Type = 'TABLE'
           AND t4.Temporary = 'Y'
           AND t4.Object_Name = t1.Table_Name)
		   AND Table_Name LIKE 'FT_%'
  ORDER BY t1.Table_Name, t1.Column_ID;


查询表以及表字段

select * from User_Tab_Comments where table_type='TABLE';
select * from User_Col_Comments where  TABLE_NAME ='IFS_CFETSFX_SWAP';
alter user sl_trd_sh profile profileIFBM;  以后那个用户访问过多，直接这样设置  IFBM库      alter user sl_trd_sh profile profileOPICS;  以后那个用户访问过多，直接这样设置  OPICS库



你查数据库当前链接数下select count(*) from v$process

查询谁在锁表并解锁
select object_name,machine,s.sid,s.serial#

from v$locked_object l,dba_objects o ,v$session s

where l.object_id　=　o.object_id and l.session_id=s.sid;
 alter system kill session '763,2504';
 alter system kill session '594,1951';
alter system kill session '2688,1232';
查询谁的电脑锁表
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;

SELECT s.sid, s.serial#, s.username, s.schemaname, s.osuser, s.process, s.machine,
s.terminal, s.logon_time, l.type
FROM v$session s, v$lock l
WHERE s.sid = l.sid
AND s.username IS NOT NULL
ORDER BY sid;

这个是查看session的消耗情况的，
select s."USERNAME",s."MACHINE",s."MODULE" ,s."STATUS",s."STATE",count(*) session个数 from v$session s group by s."MACHINE",s."USERNAME",s."MODULE" ,s."STATUS",s."STATE" 
报表制作sql技巧
select t.report_id,
       t.b,
       t.c,
       t.d,
       t.e,
       t.f,
       t.g,
       t.h,
       t.report_key_id 
  from ib_main_report_g31 t
 where t.REPORT_KEY_ID in (1, 2, 3)
union all
select * from(
select t.report_id,
       t.b,
       t.c,
       t.d,
       t.e,
       t.f,
       to_char(sum(NVL(L.REPORT_VALUE, 0))) g,
       t.h,
       t.report_key_id
  from ib_main_report_g31 T
  left join IB_MAIN_G31_LIST L
    on T.REPORT_KEY_ID = L.REPORT_KEY
  left join ib_main_approve i
    on i.deal_no = L.DEAL_NO
 where i.sponinst in ('0001')
   AND T.REPORT_KEY_ID not in (1, 2, 3)
 group by t.report_id, t.b, t.c, t.d, t.e, t.f, t.h, t.report_key_id
 )


设置表空间
select 'alter table '||table_name||' allocate extent(size 64k);' sql_text,table_name,tablespace_name
 from user_tables where table_name not in (select segment_name from user_segments where segment_type = 'TABLE');

查询某些表字段信息
SELECT t1.Table_Name || chr(13) || t3.comments       AS "表名称及说明",
       t1.Column_Name                                AS "字段名称",
       t1.DATA_TYPE || '(' || t1.DATA_LENGTH || ')'  AS "数据类型",
       t1.NullAble                                   AS "是否为空",
       t2.Comments                                   AS "字段说明",
       t1.Data_Default "默认值"
  FROM cols t1
  LEFT JOIN user_col_comments t2
         ON t1.Table_name = t2.Table_name
        AND t1.Column_Name = t2.Column_Name
  LEFT JOIN user_tab_comments t3
         ON t1.Table_name = t3.Table_name
  LEFT JOIN user_objects t4
         ON t1.table_name = t4.OBJECT_NAME
  WHERE NOT EXISTS (SELECT t4.Object_Name
          FROM User_objects t4
         WHERE t4.Object_Type = 'TABLE'
           AND t4.Temporary = 'Y'
           AND t4.Object_Name = t1.Table_Name)
  ORDER BY t1.Table_Name, t1.Column_ID
台账查询案例：
 select cash1.actint, NVL(yings.inverst_int,0) as actfint,G.DEAL_NO,G.trade_no,G.connectNo,G.SPONSOR_NAME,G.SPONINST_NAME,G.adate,di.dict_value APPROVE_STATUS ,G.prd_name,
case WHEN G.prd_no='1089' THEN dd.dict_value
     when G.prd_no='1088' then de.dict_value
     when G.prd_no='1086' then da.dict_value
     when G.prd_no='1087' then db.dict_value
else dc.dict_value end as cust_type,
G.c_no,G.c_name,G.inv_amt,G.yield,
case when G.prd_no='1088' or G.prd_no='1089' then df.dict_value else dg.dict_value end as int_fre,
dh.dict_value ccy,G.v_date,G.m_date,G.term,G.basic,G.account_name,
dj.dict_value inst_type,dn.dict_value register_rating,G.SETTL_CUST_NAME,G.party_bank_name,G.party_acc_name,
do.dict_value rate_type,dp.dict_value amt_fre,G.first_int_date,G.t_date,
case when G.prd_no='1092' or G.prd_no='1081' or G.prd_no='1087' then dx.dict_value else dq.dict_value end as inv_type,G.account_no,
G.price,dr.dict_value credit_static,ds.dict_value is_attach_deal,dt.dict_value five_type,G.risk_weight,
G.credit_type,G.fluidity_coeff,dv.dict_value amt_type,dw.dict_value financial_type,dy.dict_value terminate,
G.act_amt,G.act_yield,G.SETTL_AMT,G.ACTUAL_TINT,G.ACCRUED_TINT,G.REC_SPONSOR,G.REC_SPONINST,nvl(ie.inverst_int,0) as next_int,G.last_date,G.next_date,G.shold_INT_amt,G.not_INT_amt
from(
select T.DEAL_NO,t.trade_no,IB.Trade_No as connectNo,t.sponsor,t.sponinst,U.USER_NAME SPONSOR_NAME,I.INST_NAME SPONINST_NAME,t.adate,
     case
           when T.deal_Type = '0' or T.deal_Type = '1' then
            TTO.ORDER_STATUS
           when T.deal_Type = '2' then
            TTT.trade_STATUS
     end  as APPROVE_STATUS,
       t.prd_no,p.prd_name,t.is_flag,
       case when T.prd_no='1088' or T.prd_no='1089' then t.financial_type 
       else t.cust_type end as cust_Type,
       t.c_no,t.c_name,t.inv_amt,t.yield,t.int_fre,t.ccy,t.v_date,t.m_date,t.term,t.basic,t.payment_type,
       t.trans_flag,t.refix_fre,t.basis_rate,t.account_name,t.inst_type,t.register_rating,t.SETTL_CUST_NAME,
       t.party_bank_name,t.party_acc_name,t.rate_type, t.amt_fre,t.first_int_date,t.t_date,t.inv_type,
       t.account_no,t.price,t.credit_static,t.is_attach_deal,t.five_type,t.risk_weight,t.credit_type,
       t.fluidity_coeff,t.amt_type,t.financial_type,t.act_amt,t.act_yield,U.BRANCH_ID ubranch,I.BRANCH_ID ibranch,
       T.deal_type,M.SPONINST opsponsor,S.SETTL_AMT,S.ACTUAL_TINT,S.ACCRUED_TINT,T.REC_SPONSOR,T.REC_SPONINST,T.terminate,(select nvl(max(c.expected_date),t.V_DATE)
          from ib_main_expected_cashflow c
         where c.deal_no = t.deal_no
           and c.expected_date <=
               (select tt.cur_date from tt_dayend_date tt)) as last_date,
       (select min(c.expected_date)
          from ib_main_expected_cashflow c
         where c.deal_no = t.deal_no
           and c.expected_date >=
               (select tt.cur_date from tt_dayend_date tt)) as next_date,
        (select sum(c.inverst_int)
          from ib_main_expected_cashflow c
         where c.deal_no =t.deal_no and c.cashflow_type ='Interest'
           ) as shold_INT_amt,
           (select nvl(sum(d.inverst_int)-sum(d.actual_amt),0)
          from ib_main_expected_cashflow d
         where d.deal_no = t.deal_no and d.cashflow_type ='Interest'
           ) as not_int_amt
  from IB_MAIN_APPROVE t
   LEFT OUTER JOIN TA_USER U ON T.SPONSOR = U.USER_ID
   LEFT OUTER JOIN TT_INSTITUTION I ON T.SPONINST = I.INST_ID
   LEFT JOIN TT_TRD_trade TTT ON TTT.trade_ID = T.DEAL_NO
   LEFT JOIN TT_TRD_ORDER TTO ON TTO.ORDER_ID = T.DEAL_NO
   LEFT JOIN TC_PRODUCT P ON P.PRD_NO = T.PRD_NO
   LEFT JOIN IB_MAIN_APPROVE IB on T.Trade_No = ib.deal_no
   LEFT JOIN td_trd_tpos  S on t.DEAL_NO=s.DEAL_NO
   LEFT JOIN IB_MAIN_APPROVE M on IB.Trade_No = M.deal_no
   )G
   left join (select * from ta_dict where dict_id='ApproveStatus') di on di.dict_key=G.APPROVE_STATUS 
   left join (select * from ta_dict where dict_id='BussType') dd on dd.dict_key=G.cust_Type
   left join (select * from ta_dict where dict_id='custType') dc on dc.dict_key=G.cust_Type
   left join (select * from ta_dict where dict_id='bussFixCode') da on da.dict_key=G.cust_Type
   left join (select * from ta_dict where dict_id='bussLiveCode') db on db.dict_key=G.cust_Type
   left join (select * from ta_dict where dict_id='BussFixType') de on de.dict_key=G.cust_Type
   left join (select * from ta_dict where dict_id='payIrsFreqs') df on df.dict_key=G.int_fre
   left join (select * from ta_dict where dict_id='payIrsFreq') dg on dg.dict_key=G.int_fre
   left join (select * from ta_dict where dict_id='Currency') dh on dh.dict_key=G.ccy
   left join (select * from ta_dict where dict_id='InstitutionType') dj on dj.dict_key=G.inst_type
   left join (select * from ta_dict where dict_id='InstType') dk on dk.dict_key=G.payment_type
   left join (select * from ta_dict where dict_id='transFlag') dl on dl.dict_key=G.trans_flag
   left join (select * from ta_dict where dict_id='YesNo') dm on dm.dict_key=G.refix_fre
   left join (select * from ta_dict where dict_id='Rating') dn on dn.dict_key=G.register_rating
   left join (select * from ta_dict where dict_id='rateType') do on do.dict_key=G.RATE_TYPE 
   left join (select * from ta_dict where dict_id='CouponFrequently') dp on dp.dict_key=G.amt_fre
   left join (select * from ta_dict where dict_id='invType') dq on dq.dict_key=G.inv_type
   left join (select * from ta_dict where dict_id='YesNo') dr on dr.dict_key=G.credit_static
   left join (select * from ta_dict where dict_id='YesNo') ds on ds.dict_key=G.is_attach_deal
   left join (select * from ta_dict where dict_id='FiveType') dt on dt.dict_key=G.five_type
   left join (select * from ta_dict where dict_id='CreditType') du on du.dict_key=G.credit_type
   left join (select * from ta_dict where dict_id='YesNo') dv on dv.dict_key=G.amt_type
   left join (select * from ta_dict where dict_id='financialType') dw on dw.dict_key=G.financial_type
   left join (select * from ta_dict where dict_id='AccfiscaSubject')dx on dx.dict_key=G.inv_type
   left join (select * from ta_dict where dict_id='Terminate')dy on dy.dict_key=G.terminate
   left join (select sum(NVL(cash.actual_amt,0)) as actint,cash.deal_no as cashno from ib_main_expected_cashflow cash where cash.cashflow_type='Interest' group by cash.deal_no) cash1 on cash1.cashno=G.deal_no
   left join (select * from (
    select tibm.ACTUAL_AMT,tibm.ACTUAL_DATE,tibm.inverst_int,NVL(lag(tibm.expected_date) over(partition by tibm.deal_no order by tibm.expected_date),ibaa.v_date) as a,tibm.expected_date,tibm.deal_no from IB_MAIN_EXPECTED_CASHFLOW tibm 
    LEFT JOIN ib_main_approve ibaa on ibaa.deal_no=tibm.deal_no
    where tibm.cashflow_type='Interest') yingshou
    where yingshou.ACTUAL_AMT is null and yingshou.ACTUAL_DATE is null and to_date('2019-01-13','yyyy-MM-dd')>=to_date(yingshou.a,'yyyy-MM-dd') and to_date(yingshou.expected_date,'yyyy-MM-dd')>=to_date('2019-01-13','yyyy-MM-dd')) yings on yings.deal_no=G.deal_no
  --left join ib_main_expected_cashflow ib on G.deal_no = ib.deal_no and ib.expected_date=G.last_date and ib.cashflow_type ='Interest'
  left join ib_main_expected_cashflow ie on G.deal_no = ie.deal_no and ie.expected_date=G.next_date and ie.cashflow_type ='Interest'
 where
     G.DEAL_TYPE = '2' AND G.prd_no not in('1091','1088','1089')
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	 Encrypted:{nsw/JNwvmc4=}