FROM ubuntu
MAINTAINER <deeksha.gopal@aptiv.com>

RUN apt-get -qq update \
	&& apt-get install -y \
		wget \
		g++ \
		make \
		cmake \

RUN  apt-get update -y && \
	apt-get upgrade -y && \
     	apt-get dist-upgrade -y && \
     	apt-get -y autoremove && \
     	apt-get clean
RUN apt-get install -y p7zip \
	7zip-full \
    	unace \
    	zip \
    	unzip \
    	xz-utils \
    	sharutils \
    	uudeview \
    	mpack \
    	arj \
    	cabextract \
    	file-roller \
    	&& rm -rf /var/lib/apt/lists/*

CMD ["bash"]