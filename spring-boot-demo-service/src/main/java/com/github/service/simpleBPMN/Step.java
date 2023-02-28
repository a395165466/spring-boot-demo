package com.github.service.simpleBPMN;

import com.github.service.simpleBPMN.handler.Handler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Step {
    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * handler
     */
    private Handler stepHandler;
}
