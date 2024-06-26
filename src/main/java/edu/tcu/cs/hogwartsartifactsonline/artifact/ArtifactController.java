package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.artifact.converter.ArtifactDtoToArtifactConverter;
import edu.tcu.cs.hogwartsartifactsonline.artifact.converter.ArtifactToArtifactDtoConverter;
import edu.tcu.cs.hogwartsartifactsonline.artifact.dt0.ArtifactDto;
import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArtifactController {
  private final ArtifactService artifactService;

  private  final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;

  private final ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter;

    public ArtifactController(ArtifactService artifactService, ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter, ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter) {
        this.artifactService = artifactService;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
        this.artifactDtoToArtifactConverter = artifactDtoToArtifactConverter;
    }

    @GetMapping("/api/v1/artifacts/{artifactId}/")
    public Result findArtifactById(@PathVariable String artifactId){
       Artifact foundArtifact = this.artifactService.findById(artifactId);
       ArtifactDto artifactDto = this.artifactToArtifactDtoConverter.convert((foundArtifact));
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundArtifact);

    }

    @GetMapping("/api/v1/artifa  cts")
    public Result findAllArtifacts(){

        List<Artifact> foundArtifacts = this.artifactService.findAll();


        //Convert foundArtifacts to a list of artifactsDtos
        List<ArtifactDto> artifactDtos =  foundArtifacts.stream()
                .map(this.artifactToArtifactDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "finda All Success", artifactDtos);

    };

    @PostMapping("/api/v1/artifacts")
    public Result addArtifact(@Valid @RequestBody ArtifactDto artifactDto){

        //convert artifacdto to artifact
      Artifact newArtifact =  this.artifactDtoToArtifactConverter.convert(artifactDto);
      Artifact savedArtifact = this.artifactService.save(newArtifact);
      ArtifactDto  savedArtifactDto = this.artifactToArtifactDtoConverter.convert(savedArtifact);
      return new Result(true, StatusCode.SUCCESS, "Add Success", savedArtifactDto);
    }
}
