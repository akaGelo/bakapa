package contracts.priv.agents;

import groovy.transform.Field


@Field static def TEST_AGENT_ID = "testAgentId-1"
@Field static def TEST_AGENT_ID_2 = "testAgentId-2"

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        urlPath('/private/agents/') {
            queryParameters {
                parameter 'agentId': anyNonEmptyString()
            }
        }
    }

    response {
        status 200
        body(
                agentId: fromRequest().query("agentId"),
                password: anyUuid(),
                note: null,
                createDate: anyIso8601WithOffset(),
        )

//
        headers {
            contentType("application/json")
        }
    }

}