# Use the official Tomcat image as a parent image
FROM tomcat:9.0.46-jdk11-openjdk

# Metadata
LABEL authors="rob22"

# Set the working directory in the container to Tomcat's webapps directory
WORKDIR /usr/local/tomcat/webapps/

# Copy the .war file into the webapps directory of your container
COPY target/SimpleServlet-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Set the default command to run when starting a container from this image
CMD ["catalina.sh", "run"]