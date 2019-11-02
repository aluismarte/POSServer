package com.alsjava.courses.servers.model.communication.request;

import com.alsjava.courses.servers.model.communication.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
abstract class Request extends Base {

    private String session;
}
