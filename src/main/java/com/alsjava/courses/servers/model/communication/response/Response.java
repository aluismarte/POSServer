package com.alsjava.courses.servers.model.communication.response;

import com.alsjava.courses.servers.model.communication.Base;
import com.alsjava.courses.servers.model.communication.CommunicationCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
abstract class Response extends Base {

    protected Response() {
        code = CommunicationCodes.OK;
    }

    protected Response(int code) {
        this.code = code;
    }
}
