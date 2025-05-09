DESCRIPTION = "Accordance RCVM"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    file://ircvm.tar.gz.00;md5sum=97bd74cbff9990cee82a86652af17d31 \
    file://ircvm.tar.gz.01;md5sum=47d5b8081dfbcb6f26548a90957eea82 \
    file://ircvm.tar.gz.02;md5sum=ad421a296075f1298ecb7b099443e2d8 \
    file://ircvm.tar.gz.03;md5sum=4bf5d0e0d32f41c70670432414d34fa5 \
    file://ircvm.tar.gz.04;md5sum=4b13c7bf03808e5108182128844a8f1a \
    file://ircvm.tar.gz.05;md5sum=e7953b2c88141e8b0b4847661dabb223 \
    file://ircvm.tar.gz.06;md5sum=2d2a309210b811f081c47ce5ab6c588d \
    file://ircvm.tar.gz.07;md5sum=92c7efe9c97aae7302ca8bb08d0adda4 \
    file://ircvm.tar.gz.08;md5sum=b282acf0e2499b6bbf1faa67af68ff25 \
    file://ircvm.tar.gz.09;md5sum=25fc5aac5f9f54655280120b2787a2d0 \
    file://ircvm.tar.gz.10;md5sum=34fc1f33f754799661c1c41725f2cf37 \
    file://ircvm.tar.gz.11;md5sum=c108184e65b9f19c59c4338a3417bb51 \
    file://ircvm.tar.gz.12;md5sum=831c97e1cb9f93896361ad89f22f74f5 \
    file://ircvm.tar.gz.13;md5sum=6a5327d8ca90e843a39d2b7a9ae78db3 \
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
    install -m 0755 ${S}/rdp_run.sh ${D}/home/weston/ircvm
    install -m 0755 ${S}/setup.sh ${D}/home/weston/ircvm
    install -m 0755 ${S}/config.ini ${D}/home/weston/ircvm
    install -m 0755 ${S}/linux_run ${D}/home/weston/ircvm
}

INSANE_SKIP:${PN} += "already-stripped file-rdeps"

FILES:${PN} += "/home/weston/ircvm/*"
