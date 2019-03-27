package it.reply.labcamp.coherence.extractor;

import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.MultiExtractor;
import com.tangosol.util.extractor.PofExtractor;

import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;
import it.reply.labcamp.coherence.serializer.ProductValueSerializer;

public class ExtractorRegister {

	public static final PofExtractor<ProductValue, Category> CATEGORY_EXTRACTOR = 
			new PofExtractor<>(Category.class, ProductValueSerializer.Fields.CATEGORY.ordinal());
	
	public static final PofExtractor<ProductValue, PriceRange> PRICE_RANGE_EXTRACTOR = 
			new PofExtractor<>(PriceRange.class, ProductValueSerializer.Fields.PRICE_RANGE.ordinal());
	
	public static final MultiExtractor CATEGORY_AND_PRICE_RANGE_EXTRACTOR = 
			new MultiExtractor(new ValueExtractor[] {CATEGORY_EXTRACTOR, PRICE_RANGE_EXTRACTOR});
}
