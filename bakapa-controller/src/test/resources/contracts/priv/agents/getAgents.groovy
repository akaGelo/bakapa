package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/private/agents/'

    }

    response {
        status 200
        body([
                [
                        agent              : [
                                agentId   : $(consumer("agent1"), producer(regex("agent.+"))),
                                note      : $(consumer(anyNonBlankString()), producer(null)),
                                createDate: anyIso8601WithOffset(),
                        ],
                        backupsTargetsCount: anyNumber(),
                        status             : anyOf("OFFLINE", "ONLINE")

                ],
                [
                        agent              : [
                                agentId   : $(consumer("agent2"), producer(regex("agent.+"))),
                                note      : $(consumer(anyNonBlankString()), producer(null)),
                                createDate: anyIso8601WithOffset(),
                        ],
                        backupsTargetsCount: anyNumber(),
                        status             : anyOf("OFFLINE", "ONLINE")
                ]

        ])



        headers {
            contentType("application/json")
        }
    }

}