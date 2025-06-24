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

    public  Optional<Streaming> update(Long id, Streaming updatedStreaming) {
        Optional<Streaming> streamingUpdated = streamingRepository.findById(id);
        if(streamingUpdated.isPresent()){
            Streaming streaming = streamingUpdated.get();
            streaming.setName(updatedStreaming.getName());

            streamingRepository.save(streaming);
            return Optional.of(streaming);
        }
        return Optional.empty();
    }

    public void deletarStreamingById(Long id) {
        streamingRepository.deleteById(id);
    }
}
