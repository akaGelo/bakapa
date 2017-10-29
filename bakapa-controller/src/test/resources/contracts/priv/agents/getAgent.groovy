package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url'/private/agents/testAgentId/'
    }

    response {
        status 200
        body(
                agentId: "testAgentId",
                note: null,
                createDate: anyIso8601WithOffset(),
        )

        headers {
            contentType("application/json")
        }
    }

}