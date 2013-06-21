package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.to.TravelerSummaryTo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A traveler assembler. Creates a TravelerTo object form a given Traveler object and vice versa.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 17:40<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Component
public class TravelerAssembler {

    public Traveler createEntity(TravelerSummaryTo travelerSummary) {
        if (travelerSummary == null) {
            return null;
        }
        Traveler traveler = new Traveler();
        traveler.setId(travelerSummary.getId());
        traveler.setFirstName(travelerSummary.getFirstName());
        traveler.setLastName(travelerSummary.getLastName());
        traveler.setPassportNumber(travelerSummary.getPassportNumber());
        traveler.setPhoneNumber(travelerSummary.getPhoneNumber());
        traveler.setEmailAddress(travelerSummary.getEmailAddress());
        traveler.setCountry(travelerSummary.getCountry());
        traveler.setCity(travelerSummary.getCity());
        traveler.setStreet(travelerSummary.getStreet());
        traveler.setHouseNumber(travelerSummary.getHouseNumber());
        traveler.setZip(travelerSummary.getZip());
        return traveler;
    }

    private TravelerSummaryTo createTo(Traveler traveler) {
        if (traveler == null) {
            return null;
        }
        TravelerSummaryTo travelerSummaryTo = new TravelerSummaryTo();
        travelerSummaryTo.setId(traveler.getId());
        travelerSummaryTo.setFirstName(traveler.getFirstName());
        travelerSummaryTo.setLastName(traveler.getLastName());
        travelerSummaryTo.setPassportNumber(traveler.getPassportNumber());
        travelerSummaryTo.setPhoneNumber(traveler.getPhoneNumber());
        travelerSummaryTo.setEmailAddress(traveler.getEmailAddress());
        travelerSummaryTo.setCountry(traveler.getCountry());
        travelerSummaryTo.setCity(traveler.getCity());
        travelerSummaryTo.setStreet(traveler.getStreet());
        travelerSummaryTo.setHouseNumber(traveler.getHouseNumber());
        travelerSummaryTo.setZip(traveler.getZip());
        return travelerSummaryTo;
    }

    public List<TravelerSummaryTo> createToList(List<Traveler> travelers) {
        if (travelers == null) {
            return null;
        }
        List<TravelerSummaryTo> travelerSummaries = new ArrayList<TravelerSummaryTo>(travelers.size());
        for (Traveler traveler : travelers) {
            travelerSummaries.add(createTo(traveler));
        }
        return travelerSummaries;
    }

}