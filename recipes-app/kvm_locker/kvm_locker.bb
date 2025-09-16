SUMMARY = "Install and run kvm_locker"
DESCRIPTION = "Install kvm_locker binary and set it to auto-start via systemd"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://KVM_Locker.tar.gz.00;md5sum=b83d7092453c9b87e374301e8255ce22 \
           file://KVM_Locker.tar.gz.01;md5sum=7baec15b95fee01fa26a6216835a1de4 \
           file://KVM_Locker.tar.gz.02;md5sum=d8d092698b5a9e6999daee03e608be58 \
           file://KVM_Locker.tar.gz.03;md5sum=be210085cfba2426b0e247dd0c197950 \
           file://kvm_locker.service"

# Extraction will place files under ${WORKDIR}, so set S accordingly
S = "${WORKDIR}"

# Recombine the split tarballs and extract into ${WORKDIR}
do_untar_files () {
    cat ${WORKDIR}/KVM_Locker.tar.gz.* | tar -C ${WORKDIR} -xzf -
}
addtask untar_files before do_patch after do_unpack

inherit systemd

SYSTEMD_SERVICE:${PN} = "kvm_locker.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    # Create target directory
    install -d ${D}/home/weston/kvm_locker

    # Install main binary (case-sensitive, must match actual filename)
    install -m 0755 ${WORKDIR}/KVM_Locker ${D}/home/weston/kvm_locker/

    # Install configuration file
    install -m 0644 ${WORKDIR}/config.ini ${D}/home/weston/kvm_locker/

    # Copy images directory if present
    if [ -d ${WORKDIR}/images ]; then
        cp -a ${WORKDIR}/images ${D}/home/weston/kvm_locker/
    fi

    # Install systemd unit file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/kvm_locker.service ${D}${systemd_system_unitdir}/
}

# Skip QA check if the binary is already stripped
INSANE_SKIP:${PN} += "already-stripped"

# Runtime dependency (adjust as needed)
RDEPENDS:${PN} += "zlib"

# Files to include in the package
FILES:${PN} += " \
    /home/weston/kvm_locker \
    ${systemd_system_unitdir} \
"
