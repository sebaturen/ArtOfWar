#para compilar:
#	$  javac -d . *.java -classpath ".:/opt/tomcat/lib/servlet-api.jar:/opt/tomcat/artOfWar/ROOT/WEB-INF/classes"
#
#	-d. } genera el archivo .java con todas sus carpetas segun paquete
#	-classpath } indica la ubicacion de la libreria de servlet

echo Move to folder
cd /opt/tomcat/artOfWar/ROOT/WEB-INF/src
echo ======== START Compile ========
javac \
    DataException.java \
    gameObject/Guild.java \
    gameObject/Member.java \
    gameObject/PlayableClass.java \
    gameObject/Race.java \
    dbConnect/DBConnect.java \
    dbConnect/DBConfig.java \
    blizzardAPI/Update.java \
    blizzardAPI/APIInfo.java \
	blizzardAPI/UpdateRunningCrontab.java \
	viewController/Members.java \
    -d . -classpath ".:/opt/tomcat/lib/servlet-api.jar:/opt/tomcat/artOfWar/ROOT/WEB-INF/lib/json-simple-1.1.1.jar"
echo ======== END Compile ========
echo Delete old .class
rm -rf /opt/tomcat/artOfWar/ROOT/WEB-INF/classes/com
echo Move compile folder to class folder
cd /opt/tomcat/artOfWar/ROOT/WEB-INF/src
mv com ../classes/
echo ======== END Move ===========
