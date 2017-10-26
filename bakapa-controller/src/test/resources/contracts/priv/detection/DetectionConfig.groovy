package contracts

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("get Config")
            request {
                method 'GET'
                url '/private/detection/config'
            }

            response {
                status 200
                body([

                        "mongoPorts"     : [27017],
                        "mysqlPorts"     : [3306],
                        "postgresqlPorts": [5432]

                ])
                headers {
                    contentType("application/json")
                }
            }
        },
        Contract.make {
            name("set Config")
            request {
                method 'POST'
                urlPath('/private/detection/config') {
                    body([

                            "mongoPorts"     : [123],
                            "mysqlPorts"     : [3306,3305],
                            "postgresqlPorts": [345]

                    ])
                    headers { // (5)
                        contentType('application/json')
                    }
                }
            }

            response {
                status 200
            }
        }
]