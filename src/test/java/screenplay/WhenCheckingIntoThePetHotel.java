package screenplay;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import screenplay.tasks.CheckIn;
import screenplay.tasks.CheckOut;
import serenitylabs.tutorials.vetclinic.model.Pet;
import serenitylabs.tutorials.vetclinic.model.PetHotel;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@ExtendWith(SerenityJUnit5Extension.class)

public class WhenCheckingIntoThePetHotel {

    @Test
    public void petra_books_her_cat_into_the_hotel() {

        //Given
        Actor petra = Actor.named("Petra the pet owner");
        Pet ginger = Pet.cat().named("Ginger");
        PetHotel petHotel = PetHotel.called("Pet Hotel California");

        //When
        petra.attemptsTo(
                CheckIn.aPet(ginger).into(petHotel)
        );

        // Then
        assertThat(petHotel.getPets(), hasItem(ginger));
    }

    @Test
    public void petra_checks_her_pet_out_of_the_hotel() {

        //Given
        Actor petra = Actor.named("Petra the pet owner");
        Pet ginger = Pet.cat().named("Ginger");
        PetHotel petHotel = PetHotel.called("Pet Hotel California");

        petra.wasAbleTo(CheckIn.aPet(ginger).into(petHotel));

        //When
        petra.attemptsTo(CheckOut.aPet(ginger).from(petHotel));

        //Then
        assertThat(petHotel.getPets(), not(hasItem(ginger)));
    }
}
