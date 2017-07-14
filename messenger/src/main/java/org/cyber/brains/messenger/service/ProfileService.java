package org.cyber.brains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cyber.brains.messenger.database.DatabaseClass;
import org.cyber.brains.messenger.model.Profile;

public class ProfileService 
{
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService()
	{
		profiles.put("Cyber", new Profile(1L, "Cyber", "Cyber", "Smoke"));
	}
	
	public Profile addProfile( Profile profile )
	{
		profile.setId( profiles.size() + 1 );
		profiles.put( profile.getProfileName(), profile);
		return profile;
	}
	
	public void removeProfile( String profileName )
	{
		profiles.remove( profileName );
	}
	
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>( profiles.values() );
	}
	
	public Profile getProfile( String profileName )
	{
		return profiles.get(profileName);
	}
	
	public Profile updateProfile( Profile profile )
	{
		if( profile.getProfileName() == null )
		{
			return null;
		}
		
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
}
