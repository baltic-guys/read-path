package com.baltic.road.path.app.gr

import spock.lang.Shared
import spock.lang.Specification

class SimpleGRClientSpec extends Specification {

    @Shared
    def apiKey

    @Shared
    def userId

    def setupSpec() {
        apiKey = 'qF3rQniJYU52xvIHtbYFA'
        userId = '108999484'
    }

    def 'SimpleGRClient should return response'() {
        given:
        def query = "https://www.goodreads.com/user/show/${userId}.xml?key=${apiKey}"

        when:
        def response = SimpleGRClient.request(query)

        then:
        noExceptionThrown()
        !response.isBlank()
    }

    def 'SimpleGRClient should return shelves list'() {
        given:
        def query = "https://www.goodreads.com/shelf/list.xml?key=${apiKey}&user_id=${userId}"

        when:
        def response = SimpleGRClient.request(query)
        def node = parseXml(response)

        then:
        noExceptionThrown()
        node.shelves.user_shelf.size() == 3
    }


    private def parseXml(xml) {
        new XmlSlurper().parseText(xml)
    }

}