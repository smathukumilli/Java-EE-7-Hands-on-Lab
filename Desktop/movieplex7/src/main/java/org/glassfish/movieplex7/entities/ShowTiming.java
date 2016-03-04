/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "SHOW_TIMINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShowTiming.findAll",
            query = "SELECT s FROM ShowTiming s"),
    @NamedQuery(name = "ShowTiming.findById",
            query = "SELECT s FROM ShowTiming s WHERE s.id = :id"),
    @NamedQuery(name = "ShowTiming.findByMovieAndTimeslotId",
            query = "SELECT s FROM ShowTiming s WHERE s.movie.id = :movieId AND s.timeslot.id = :timeslotId"),
    @NamedQuery(name = "ShowTiming.findByDay",
            query = "SELECT s FROM ShowTiming s WHERE s.day = :day")})
public class ShowTiming implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;

    @NotNull
    private int day;

    @JoinColumn(name = "TIMESLOT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Timeslot timeslot;

    @JoinColumn(name = "THEATER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Theater theater;

    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Movie movie;

    public ShowTiming() {
    }

    public ShowTiming(Integer id) {
        this.id = id;
    }

    public ShowTiming(Integer id, int day) {
        this.id = id;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShowTiming)) {
            return false;
        }
        ShowTiming other = (ShowTiming) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return movie.getName() + ", " + timeslot.getStartTime();
    }
}