package it.reply.labcamp.coherence.extractor;

import com.tangosol.io.pof.reflect.SimplePofPath;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.MultiExtractor;
import com.tangosol.util.extractor.PofExtractor;

import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;
import it.reply.labcamp.coherence.serializer.ProductValueSerializer;
/*
 * https://docs.oracle.com/middleware/12213/coherence/java-reference/com/tangosol/util/extractor/MultiExtractor.html
 */
public class ExtractorRegister {

	public static final PofExtractor<ProductValue, Category> CATEGORY_EXTRACTOR = 
			new PofExtractor<>(Category.class, new SimplePofPath(new int[] {ProductValueSerializer.Fields.CATEGORY.ordinal()}));
	
	public static final PofExtractor<ProductValue, PriceRange> PRICE_RANGE_EXTRACTOR = 
			new PofExtractor<>(PriceRange.class, new SimplePofPath(new int[] {ProductValueSerializer.Fields.PRICE_RANGE.ordinal()}));
	
	public static final MultiExtractor CATEGORY_AND_PRICE_RANGE_EXTRACTOR = 
			new MultiExtractor(new ValueExtractor[] {CATEGORY_EXTRACTOR, PRICE_RANGE_EXTRACTOR});
}
