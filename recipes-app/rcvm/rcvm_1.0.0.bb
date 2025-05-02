DESCRIPTION = "Accordance RCVM"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    file://ircvm.tar.gz.00;md5sum=104cc34c23964623f93eefba9c2abf59 \
    file://ircvm.tar.gz.01;md5sum=0a0f8a02656fb4ac1948ba868e05f1a4 \
    file://ircvm.tar.gz.02;md5sum=d9226c820f159fa93bd983d15867be19 \
    file://ircvm.tar.gz.03;md5sum=2cbdff699c249256771ea4ebc8eab4dd \
    file://ircvm.tar.gz.04;md5sum=5cc0bdcd4ae9277ba9015b10ce8c1be2 \
    file://ircvm.tar.gz.05;md5sum=405f00d927673d9afca5fde765ad48d4 \
    file://ircvm.tar.gz.06;md5sum=b78b40ff796c53180453fc1c41330a24 \
    file://ircvm.tar.gz.07;md5sum=b048cad96e732b7c86108bb5d3ac7016 \
    file://ircvm.tar.gz.08;md5sum=b8510bae3e2f0d0f602b073f73e7dffa \
    file://ircvm.tar.gz.09;md5sum=6e34636caf3784491af22a6f5dd9bdcf \
    file://ircvm.tar.gz.10;md5sum=2700fa0105e75769fa1deaaacbe604f3 \
    file://ircvm.tar.gz.11;md5sum=e69d51263f588c2ed06d59babd28847c \
    file://ircvm.tar.gz.12;md5sum=9e3cddaa9cabe3c5ba18c6a0054897e7 \
"

S = "${WORKDIR}/ircvm"

do_untar_files () {
	cat ${WORKDIR}/ircvm.tar.gz.* | tar -C ${WORKDIR} -zxvf -
}
addtask untar_files before do_patch after do_unpack

do_install:append () {
	install -d -m 0755 ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_config.ini ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_run ${D}/home/weston/ircvm
        install -m 0755 ${S}/vnc_snapshot ${D}/home/weston/ircvm
        install -m 0755 ${S}/rcvm_run.sh ${D}/home/weston/ircvm
        install -m 0755 ${S}/rdp_run.sh ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_run.sh ${D}/home/weston/ircvm
        install -m 0755 ${S}/setup.sh ${D}/home/weston/ircvm
}

INSANE_SKIP:${PN} += "already-stripped file-rdeps"

FILES:${PN} += "/home/weston/ircvm/*"

