package com.baltic.road.path.app.gr.client

import spock.lang.Shared
import spock.lang.Specification

import static com.baltic.road.path.app.gr.client.SimpleGRClient.SearchType.ALL
import static com.baltic.road.path.app.gr.client.SimpleGRClient.SearchType.TITLE

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

    def 'SimpleGRClient should return search xml response by TITLE'() {
        given:
        def title = 'Ender\'s Game'

        when:
        def response = SimpleGRClient.search(apiKey, title, 1, TITLE)
        def node = parseXml(response)

        then:
        noExceptionThrown()
        node.search.results.work.size() > 0
        node.search.results.work.size() == node.search['results-end'].text() as Integer
    }

    def 'SimpleGRClient should return search xml response by ISBN'() {
        given:
        def isbn = '1617294942'

        when:
        def response = SimpleGRClient.search(apiKey, isbn, 1, ALL)
        def node = parseXml(response)

        then:
        noExceptionThrown()
        node.search.results.work.size() > 0
        node.search.results.work.size() == node.search['results-end'].text() as Integer
    }

    private def parseXml(xml) {
        new XmlSlurper().parseText(xml)
    }

}