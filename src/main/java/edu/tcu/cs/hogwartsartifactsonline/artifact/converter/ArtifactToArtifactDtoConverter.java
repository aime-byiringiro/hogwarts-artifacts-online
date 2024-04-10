package edu.tcu.cs.hogwartsartifactsonline.artifact.converter;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.artifact.dt0.ArtifactDto;
import edu.tcu.cs.hogwartsartifactsonline.wizard.converter.WizardToWizardDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ArtifactToArtifactDtoConverter  implements Converter<Artifact, ArtifactDto> {

    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;

    public ArtifactToArtifactDtoConverter(WizardToWizardDtoConverter wizardToWizardDtoConverter) {
        this.wizardToWizardDtoConverter = wizardToWizardDtoConverter;
    }

    @Override
    public ArtifactDto convert(Artifact source) {

        ArtifactDto artifactDto = new ArtifactDto(source.getId(),
                                                  source.getDescription(),
                                                  source.getImageUrl(),
                                                  source.getOwner() != null
                                                            ? String.valueOf(this.wizardToWizardDtoConverter.convert(source.getOwner())) :null);

        return artifactDto;
    }
}
