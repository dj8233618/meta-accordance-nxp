DESCRIPTION = "Accordance WebPage"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://database_setup.zip;md5sum=9c2c72422fd9d638a703b6e488cc6395"

S = "${WORKDIR}/webpages"

do_install:append () {
	install -d -m 0755 ${D}/var/www/html
	cp -r ${S}/* ${D}/var/www/html
}

FILES:${PN} += "/var/www/html"

