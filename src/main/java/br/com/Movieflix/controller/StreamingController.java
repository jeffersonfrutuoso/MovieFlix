package br.com.Movieflix.controller;

import br.com.Movieflix.controller.request.StreamingRequest;
import br.com.Movieflix.controller.response.StreamingResponse;
import br.com.Movieflix.entity.Streaming;
import br.com.Movieflix.mapper.CategoryMapper;
import br.com.Movieflix.mapper.StreamingMapper;
import br.com.Movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        List<StreamingResponse> streamings = streamingService.findAllStreaming()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streamings);
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> addStreaming(@RequestBody StreamingRequest streamingRequest) {
        Streaming newStreaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming savedStreaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id){
        return streamingService.findStreamingById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStreaming(@PathVariable Long id) {
        streamingService.deletarStreamingById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
