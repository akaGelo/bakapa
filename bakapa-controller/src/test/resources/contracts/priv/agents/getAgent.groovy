package contracts.priv.agents

import groovy.transform.Field

@Field static def TEST_AGENT_ID = "testAgentId-1"

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url "/private/agents/${TEST_AGENT_ID}/"
    }

    response {
        status 200
        body(
                agentId: TEST_AGENT_ID,
                note: null,
                createDate: anyIso8601WithOffset(),
        )

        headers {
            contentType("application/json")
        }
    }

}