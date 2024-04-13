package edu.tcu.cs.hogwartsartifactsonline.artifact.converter;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.artifact.dt0.ArtifactDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.MarshalException;

@Component
public class ArtifactDtoToArtifactConverter implements Converter<ArtifactDto, Artifact> {


    @Override
    public Artifact convert(ArtifactDto source) {


        Artifact artifact = new Artifact();
        artifact.setId(source.id());
        artifact.setName(source.name());
        artifact.setDescription(source.description());
        artifact.setImageUrl(source.imageUrl());
        return artifact;
    }
}
