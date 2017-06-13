/*
 *
 *  Copyright 2012 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.netflix.simianarmy.janitor;

import java.util.EnumSet;
import java.util.List;

import com.netflix.simianarmy.Resource;
import com.netflix.simianarmy.ResourceType;

/**
 * The crawler for janitor monkey.
 */
public interface JanitorCrawler {

    /**
     * Resource types.
     *
     * @return the type of resources this crawler crawls
     */
    EnumSet<? extends ResourceType> resourceTypes();

    /**
     * Resources crawled by this crawler for a specific resource type.
     *
     * @param resourceType the resource type
     * @return the list
     */
    List<Resource> resources(ResourceType resourceType);

    /**
     * Gets the up to date information for a collection of resource ids. When the input argument is null
     * or empty, the method returns all resources.
     *
     * @param resourceIds
     *          the resource ids
     * @return the list of resources
     */
    List<Resource> resources(String... resourceIds);

    /**
     * Gets the owner email for a resource to set the ownerEmail field when crawl.
     * @param resource the resource
     * @return the owner email of the resource
     */
    String getOwnerEmailForResource(Resource resource);

    default void setTagsToResource(JsonNode jsonNode, Resource resource) {
        JsonNode tags = jsonNode.get("tags");
        if (tags == null || !tags.isArray() || tags.size() == 0) {
            LOGGER.debug(String.format("No tags is found for %s", resource.getId()));
        } else {
            for (Iterator<JsonNode> it = tags.getElements(); it.hasNext();) {
                JsonNode tag = it.next();
                String key = tag.get("key").getTextValue();
                String value = tag.get("value").getTextValue();
                resource.setTag(key, value);
            }
        }
    }
}
