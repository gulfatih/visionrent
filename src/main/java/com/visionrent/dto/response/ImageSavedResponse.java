package com.visionrent.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ImageSavedResponse extends VRResponse {

    private UUID imageId;

    public ImageSavedResponse(UUID imageId , String message, boolean success) {
        super(message, success);
        this.imageId = imageId;
    }

}
