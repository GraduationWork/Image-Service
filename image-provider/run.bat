set JAVA_HOME=d:\Tools\Java8
set MAVEN_OPTS=-Xdebug -Xmx512m -Xrunjdwp:transport=dt_socket,address=8004,server=y,suspend=n
call mvn -Djetty.port=8084 jetty:run
pause