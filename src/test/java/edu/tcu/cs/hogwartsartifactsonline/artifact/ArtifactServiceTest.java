package edu.tcu.cs.hogwartsartifactsonline.artifact;
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

    @InjectMocks // This will  tell Mock that is the the service  will need to use fake date from mock
    ArtifactService artifactService;



    @BeforeEach
    void setUp() {
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



}