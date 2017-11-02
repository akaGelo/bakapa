package contracts.priv.config

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("get detection Config")
            request {
                method 'GET'
                url '/private/config/detection/'
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
            name("set detection config")
            request {
                method 'POST'
                urlPath('/private/config/detection/') {
                    body([

                            "mongoPorts"     : $(consumer(regex("[0-9,]*")), producer([27017])),
                            "mysqlPorts"     : $(consumer(regex("[0-9,]*")), producer([3306])),
                            "postgresqlPorts": $(consumer(regex("[0-9,]*")), producer([5432]))

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