package br.com.Movieflix.service;

import br.com.Movieflix.entity.Streaming;
import br.com.Movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;

    public List<Streaming> findAllStreaming () {
        return streamingRepository.findAll();
    }

    public Streaming saveStreaming(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findStreamingById(Long id) {
        return streamingRepository.findById(id);
    }

    public void deletarStreamingById(Long id) {
        streamingRepository.deleteById(id);
    }
}
