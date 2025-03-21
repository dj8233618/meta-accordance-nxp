DESCRIPTION = "Accordance RCVM"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI = "file://ircvm.tar.gz;md5sum="

S = "${WORKDIR}/irvcm"

do_install:append () {
	install -d -m 0755 ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_config.ini ${D}/home/weston/ircvm
        install -m 0755 ${S}/svm_run ${D}/home/weston/ircvm
        install -m 0755 ${S}/vnc_snapshot ${D}/home/weston/ircvm
}

FILES:${PN} += "/home/weston/ircvm/*"

