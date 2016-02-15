set JAVA_HOME=d:\Tools\Java8
set MAVEN_OPTS=-Xdebug -Xmx512m -Xrunjdwp:transport=dt_socket,address=8003,server=y,suspend=n
call mvn -Djetty.port=8083 jetty:run
pause