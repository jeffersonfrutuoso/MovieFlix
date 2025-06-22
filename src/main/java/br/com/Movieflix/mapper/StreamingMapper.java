package br.com.Movieflix.mapper;

import br.com.Movieflix.controller.request.StreamingRequest;
import br.com.Movieflix.controller.response.StreamingResponse;
import br.com.Movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming.builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
