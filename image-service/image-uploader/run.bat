set MAVEN_OPTS=-Xdebug -Xmx512m -Xrunjdwp:transport=dt_socket,address=8006,server=y,suspend=n
call mvn -Djetty.port=8086 jetty:run
pause