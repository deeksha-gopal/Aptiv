

FROM jenkins/jenkins:2.346.2-jdk11
LABEL "author"="Aptiv"

ARG JAVA_OPTS
ARG resourcesPath=resources

ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Dhudson.model.DownloadService.noSignatureCheck=true ${JAVA_OPTS:-}"
ENV CURL_OPTIONS -sSfLk -C -
COPY --chown=jenkins ${resourcesPath}/jenkins-casc.yaml /var/jenkins_home/casc_configs/
COPY --chown=jenkins ${resourcesPath}/plugins.txt /usr/share/jenkins/ref/plugins.txt

USER root
RUN apt-get update \
    && apt-get install -y apt-utils \
    && apt-get upgrade -y \
    && apt-get dist-upgrade -y \
    && apt-get install -y apt-transport-https wget vim \
    && apt-get install -y python3.9-full python3-pip gcc-10 g++-10 g++ cmake \
    && java -jar /opt/jenkins-plugin-manager.jar -f /usr/share/jenkins/ref/plugins.txt

# Update permissions and ownership
RUN chown -R jenkins:jenkins /var/jenkins_home/  \
    && chown jenkins:jenkins -R /var/jenkins_home/casc_configs

USER jenkins
WORKDIR /var/jenkins_home
