package com.angelsheaven.demo.utilities

import androidx.room.TypeConverter
import com.angelsheaven.demo.data.storage.Enclosure
import com.google.gson.Gson

/**
 * DataConverters class - used to help room convert data type for storage purpose
 * @author Quan Nguyen
 * @see AppDatabase
 */
class DataConverters{

    /**
     * Convert list of Categories to json
     * @param categoriesList list of categories need to be converted
     * @return A Json String of converted data
     */
    @TypeConverter
    fun fromCategoriesValueToList(categoriesList:List<String>):String?{
        categoriesList?.run {
            return Gson().toJson(this)
        }
        return null
    }

    /**
     * Convert A json string to a string list of Categories
     * @param categoriesJsonStringValue
     * @return A converted String list of Categories
     */
    @TypeConverter
    fun jsonToCategoriesValues(categoriesJsonStringValue:String):List<String>?{
        val objects:Array<String>? = Gson().fromJson(categoriesJsonStringValue,Array<String>::class.java)
        return objects?.toList()
    }

    /**
     * Convert an enclosure object to json string
     * @param enclosure want to convert
     * @return A string json of converted object
     */
    @TypeConverter
    fun fromEnclosureValueToList(enclosure:Enclosure):String?{
        enclosure?.run {
            return Gson().toJson(this)
        }
        return null
    }

    /**
     * Convert a string json to enclose object
     * @param enclosureJsonStringValue json string value of wanted enclosure object
     * @return converted Enclosure object
     */
    @TypeConverter
    fun jsonToEnclosure(enclosureJsonStringValue:String): Enclosure? {
        return Gson().fromJson(enclosureJsonStringValue,Enclosure::class.java)
    }
}
