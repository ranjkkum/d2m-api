FROM ranjkkum.jfrog.io/d2m-docker-local/d2m-base-server:1.0

# Labels
LABEL com.oracle.image.maintainer.name="Ranjan Kumar" \
      com.oracle.image.maintainer.email="ranjan.k.kumar@oracle.com" \
      com.oracle.image.description="Apache Tomcat docker image for D2M Server" \
      com.oracle.image.type="server"

ARG APP_NAME
ARG BUILD_VERSION

ENV APP_NAME=$APP_NAME \
    BUILD_VERSION=$BUILD_VERSION \
    ROOT_USER=root \
    ORACLE_USER=oracle \
    GROUP_ID=dba \
    GID=1010 \
    UID=1005 \
    TOMCAT_MAJOR=9 \
    TOMCAT_MINOR=9.0.58 \
    TOMCAT_INSTALL_DIR=/u01/server \
    CATALINA_HOME=${TOMCAT_INSTALL_DIR}/apache-tomcat-${TOMCAT_MINOR}

RUN mkdir -p $CATALINA_HOME/builds
ADD ${APP_NAME}-${BUILD_VERSION}-SNAPSHOT.war ${CATALINA_HOME}/builds
RUN chown -R ${ORACLE_USER}:${GROUP_ID} ${CATALINA_HOME}/builds

USER ${ORACLE_USER}
WORKDIR $CATALINA_HOME
RUN ln -sf ../builds/${APP_NAME}-${BUILD_VERSION}.war webapps/ROOT.war
EXPOSE 8080