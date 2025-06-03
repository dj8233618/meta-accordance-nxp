
SUMMARY = "Install and run ping_dev"
DESCRIPTION = "Install ping_dev binary and set it to auto-start via systemd"
LICENSE = "CLOSED"
SRC_URI = "file://ping_dev \
           file://ping_dev.service"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "ping_dev.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}/home/weston/ircvm
    install -m 0755 ${WORKDIR}/ping_dev ${D}/home/weston/ircvm
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ping_dev.service ${D}${systemd_system_unitdir}
}
