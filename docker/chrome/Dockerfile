FROM selenium/standalone-chrome-debug:3.141.59-vanadium

USER root

# Install apt packages
RUN apt -y update
RUN apt -y install rinetd
RUN apt -y autoremove && \
  apt -y clean

USER seluser

COPY sidecar-wrapper.sh /opt/bin/sidecar-wrapper.sh

ENTRYPOINT ["/bin/bash", "/opt/bin/sidecar-wrapper.sh"]