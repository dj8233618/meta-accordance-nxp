
SUMMARY = "Install and run kvm_locker"
DESCRIPTION = "Install kvm_locker binary and set it to auto-start via systemd"
LICENSE = "CLOSED"
SRC_URI = "file://kvm_locker \
           file://kvm_locker.service"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "kvm_locker.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}/home/weston/kvm_locker
    install -m 0755 ${WORKDIR}/kvm_locker ${D}/home/weston/kvm_locker
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/kvm_locker.service ${D}${systemd_system_unitdir}
}

INSANE_SKIP:${PN} += "already-stripped"
RDEPENDS:${PN} += "zlib"
FILES:${PN} += "/home/weston/ircvm ${systemd_system_unitdir}"
