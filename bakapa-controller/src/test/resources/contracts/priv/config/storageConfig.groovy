package contracts

import org.springframework.cloud.contract.spec.Contract


/**
 * Scene
 * 1) updateConfig
 * 2) get config
 *
 */

[
        Contract.make {
            name("set storage config")
            request {
                method 'POST'
                urlPath("/private/config/storage/") {
                    body([
                            endpoint : $(consumer(anyNonEmptyString()), producer(anyUrl())),
                            bucket   : $(consumer(anyNonEmptyString()), producer(anyNonEmptyString())),
                            accessKey: $(consumer(anyNonEmptyString()), producer(anyNonEmptyString())),
                            secretKey: $(consumer(anyNonEmptyString()), producer(anyNonEmptyString())),

                    ])
                }

                headers {
                    contentType(applicationJson())
                }
            }

            response {
                status 200
            }
        },

        Contract.make {
            name("get storage config")
            request {
                method 'GET'
                url "/private/config/storage/"
            }

            response {
                status 200
                body([
                        endpoint : $(consumer(anyUrl()), producer(anyUrl())),
                        bucket   : $(consumer(anyNonEmptyString()), producer(anyNonEmptyString())),
                        accessKey: $(consumer(anyNonEmptyString()), producer(anyNonEmptyString())),

                ])


                headers {
                    contentType("application/json")
                }
            }
        }


]