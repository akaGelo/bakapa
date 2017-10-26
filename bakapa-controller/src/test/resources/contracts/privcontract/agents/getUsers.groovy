package contracts.agents

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/private/agents/'

    }

    response {
        status 200
        body([
                [
                        agentId   : $(consumer("agent1"), producer(regex("agent.+"))),
                        note      : $(consumer(anyNonBlankString()), producer(null)),
                        createDate: anyIso8601WithOffset(),
                ],
                [
                        agentId   : $(consumer("agent2"), producer(regex("agent.+"))),
                        note      : $(consumer(anyNonBlankString()), producer(null)),
                        createDate: anyIso8601WithOffset(),
                ]

        ])



        headers {
            contentType("application/json")
        }
    }

}