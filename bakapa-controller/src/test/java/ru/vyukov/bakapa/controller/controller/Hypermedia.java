package ru.vyukov.bakapa.controller.controller;

import org.springframework.restdocs.hypermedia.HypermediaDocumentation;
import org.springframework.restdocs.hypermedia.LinkDescriptor;
import org.springframework.restdocs.hypermedia.LinksSnippet;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;

public class Hypermedia {
	
	

	public static LinksSnippet links(LinkDescriptor... descriptors) {
		return HypermediaDocumentation.links(
				//
				linkWithRel("_self").ignored().optional(), //
				linkWithRel("curies").ignored())//

				.and(descriptors);
	}

}