package edu.tcu.cs.hogwartsartifactsonline.artifact;
import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock //  This is will tell Mock that this is the repo we want to simulate
    ArtifactRepository artifactRepository;


    @Mock
    IdWorker idWorker;

    @InjectMocks // This will  tell Mock that is the the service  will need to use fake date from mock
    ArtifactService artifactService;
    List<Artifact> artifacts;



    @BeforeEach
    void setUp() {

        Artifact a1 = new Artifact();
        a1.setId("");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device inventd by Albus Dumbledore that resemble");
        a1.setImageUrl("imageUrl");

        Artifact a2 = new Artifact();
        a2.setId("");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible");
        a2.setImageUrl("imageUrl");


        this.artifacts = new ArrayList<>();
        this.artifacts.add(a1);
        this.artifacts.add(a2);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
/*
       Given. Where you prepare the behavior  of the fake date
        Arrange inputs and targets. Define the behavior of Mock object artifactrepositor
  */
        Artifact a = new Artifact();
        a.setId("1250808601744904192");
        a.setName("Invisibility Cloak");
        a.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a.setImageUrl("ImageUrl");
        // creating the fake wizard owner of the artifact
        Wizard w = new Wizard();
        w.setId(2);
        w.setName("Harry Potter");

        a.setOwner(w);
        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(a));




        // When. Act on the target behavior. When steps should cover the method o be tested.

        Artifact returnedArtifact = this.artifactService.findById("1250808601744904192");
        // Then. Comparing the expected results to the produced results by the mock, assert expected outcomes.

        assertThat(returnedArtifact.getId()).isEqualTo(a.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(a.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(a.getImageUrl());
        assertThat(returnedArtifact.getOwner()).isNotNull();
        verify(this.artifactRepository, times(1)).findById("1250808601744904192");

    }

    @Test
    void testFindByIdNoFound(){

        //Given

        given(this.artifactRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        // When
        // If artifact throws any exception, then  catchThrowable will catch the expectation and assign the exception
        // to thrown variable so that we can use it in "the then" part

       Throwable thrown = catchThrowable(()-> {
           Artifact returnedArtifact = this.artifactService.findById("1250808601744904192");
        });
        // Then
        assertThat(thrown)
                .isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Could not find artifact with Id 1250808601744904192 :( ");
        verify(this.artifactRepository, times(1)).findById("1250808601744904192");


    }


    @Test
    void testFindAllSuccess(){
        //Given
        given(artifactRepository.findAll()).willReturn(this.artifacts);


        //When
        List<Artifact> actualArtifacts = artifactService.findAll();


        //Then

        assertThat(actualArtifacts.size()).isEqualTo(this.artifacts.size());

        verify(artifactRepository, times(1)).findAll();

    }



    @Test
    void testSaveSuccess(){
        //Given

        Artifact newArtifact = new Artifact();
        newArtifact.setName("Artifact 3");
        newArtifact.setDescription("Description....");
        newArtifact.setImageUrl("ImageUrl.....");


        given(idWorker.nextId()).willReturn(123456L); // returning a fake id
        given(artifactRepository.save(newArtifact)).willReturn(newArtifact);


        //When

        Artifact savedArtifact = artifactService.save(newArtifact);

        //Then

        assertThat(savedArtifact.getId()).isEqualTo("123456 ");
        assertThat(savedArtifact.getName()).isEqualTo(newArtifact.getName());
        assertThat(savedArtifact.getDescription()).isEqualTo(newArtifact.getDescription());
        assertThat(savedArtifact.getImageUrl()).isEqualTo(newArtifact.getImageUrl());

        verify(artifactRepository, times(1)).save(newArtifact);



    }



}