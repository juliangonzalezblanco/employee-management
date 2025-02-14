FROM tomcat:9.0

COPY war-folder/thales-app.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 4200

RUN sed -i 's/port="8080"/port="4200"/g' /usr/local/tomcat/conf/server.xml

CMD ["catalina.sh", "run"]