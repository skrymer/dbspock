package org.skrymer.dbunit.extras.spock

import spock.lang.Specification

@DBUnitConfig(dataSetLocation = 'some/location')
class DBUnitConfigSpec extends Specification {

    def setup() {
        println "setup"
    }

    def setupSpec() {
        println "setupSpec"
    }

    def cleanup(){
        println "cleanup"
    }

    def cleanupSpec(){
        println "cleanUpSpec"
    }

    def 'should add dataSetLocation to system properties' () {
        expect:
        System.getProperty('dbunit.dataSetLocation') == 'some/location'
    }

    @Insert(fileNames = ['file1', 'file2', 'file3'])
    def '' () {

    }
}
