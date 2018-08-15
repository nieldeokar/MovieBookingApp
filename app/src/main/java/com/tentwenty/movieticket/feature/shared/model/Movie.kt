package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tentwenty.movieticket.utils.constants.DBConstants

@Entity(tableName = DBConstants.MOVIES_TABLE_NAME)
class Movie() : Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "m_id")
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int = 0

    @ColumnInfo(name = "video")
    @SerializedName("video")
    var video: Boolean = false

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Double = 0.toDouble()

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var originalTitle: String? = null

    /*@ColumnInfo(name = "genre_id")
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null*/

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    var adult: Boolean = false

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        voteCount = parcel.readInt()
        video = parcel.readByte() != 0.toByte()
        voteAverage = parcel.readDouble()
        title = parcel.readString()
        popularity = parcel.readDouble()
        posterPath = parcel.readString()
        originalLanguage = parcel.readString()
        originalTitle = parcel.readString()
        backdropPath = parcel.readString()
        adult = parcel.readByte() != 0.toByte()
        overview = parcel.readString()
        releaseDate = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeString(title)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }


}
