DESCRIPTION = "Accordance RCVM"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}:files"

SRC_URI = " \
file://ircvm.tar.gz.00;md5sum=919caa0c1a5db27b0a0255735945f2ee \
file://ircvm.tar.gz.01;md5sum=b3e6dba8732d9631308fff69acebac80 \
file://ircvm.tar.gz.02;md5sum=8003679582b279ad61533af1630e7b42 \
file://ircvm.tar.gz.03;md5sum=3683c082ff531c6846ffb6ef2c8c0932 \
file://ircvm.tar.gz.04;md5sum=6f9b45e77e40af289416d947a63bd5e8 \
"

S = "${WORKDIR}/rcvm"

do_untar_files () {
	cat ${WORKDIR}/ircvm.tar.gz.* | tar -C ${WORKDIR} -zxvf -
}
addtask untar_files before do_patch after do_fetch

do_install:append () {
	install -d -m 0755 ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_config.ini ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_run ${D}/home/weston/ircvm
        install -m 0755 ${S}/vnc_snapshot ${D}/home/weston/ircvm
}

INSANE_SKIP:${PN} += "already-stripped file-rdeps"

FILES:${PN} += "/home/weston/ircvm/*"

